#!/bin/bash

# GreenCode Database Backup Script
# This script creates database backups for different environments

set -e

# Configuration
BACKUP_DIR="backups"
DATE_FORMAT="%Y%m%d_%H%M%S"
RETENTION_DAYS=30

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Default values
ENVIRONMENT="dev"
BACKUP_TYPE="full"
COMPRESS=true

# Help function
show_help() {
    echo "Usage: $0 [OPTIONS]"
    echo ""
    echo "Options:"
    echo "  -e, --environment ENV    Environment (dev, staging, prod) [default: dev]"
    echo "  -t, --type TYPE          Backup type (full, schema, data) [default: full]"
    echo "  -c, --compress           Compress backup files [default: true]"
    echo "  -r, --retention DAYS     Keep backups for N days [default: 30]"
    echo "  -h, --help               Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 -e dev                # Backup development database"
    echo "  $0 -e prod -t schema     # Backup production schema only"
    echo "  $0 -e staging -r 7       # Keep staging backups for 7 days"
}

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -e|--environment)
            ENVIRONMENT="$2"
            shift 2
            ;;
        -t|--type)
            BACKUP_TYPE="$2"
            shift 2
            ;;
        -c|--compress)
            COMPRESS="$2"
            shift 2
            ;;
        -r|--retention)
            RETENTION_DAYS="$2"
            shift 2
            ;;
        -h|--help)
            show_help
            exit 0
            ;;
        *)
            echo -e "${RED}❌ Unknown option: $1${NC}"
            show_help
            exit 1
            ;;
    esac
done

# Validate backup type
case $BACKUP_TYPE in
    full|schema|data)
        ;;
    *)
        echo -e "${RED}❌ Invalid backup type: $BACKUP_TYPE${NC}"
        echo "Valid types: full, schema, data"
        exit 1
        ;;
esac

echo "🗄️  Creating GreenCode Database Backup..."
echo "Environment: $ENVIRONMENT"
echo "Backup Type: $BACKUP_TYPE"
echo "Compression: $COMPRESS"
echo "Retention: $RETENTION_DAYS days"
echo ""

# Create backup directory
create_backup_directory() {
    echo "📁 Creating backup directory..."
    mkdir -p "$BACKUP_DIR/$ENVIRONMENT"
    echo -e "${GREEN}✅ Backup directory created${NC}"
}

# Get database configuration
get_database_config() {
    echo "🔧 Reading database configuration..."
    
    # Load environment variables
    if [ -f ".env" ]; then
        source .env
    fi
    
    # Set default values based on environment
    case $ENVIRONMENT in
        dev)
            DB_HOST=${DB_HOST:-"localhost"}
            DB_PORT=${DB_PORT:-"5432"}
            DB_NAME=${DB_NAME:-"greencode"}
            DB_USER=${DB_USER:-"postgres"}
            DB_PASSWORD=${DB_PASSWORD:-"password"}
            ;;
        staging)
            DB_HOST=${STAGING_DB_HOST:-"localhost"}
            DB_PORT=${STAGING_DB_PORT:-"5432"}
            DB_NAME=${STAGING_DB_NAME:-"greencode_staging"}
            DB_USER=${STAGING_DB_USER:-"postgres"}
            DB_PASSWORD=${STAGING_DB_PASSWORD:-"password"}
            ;;
        prod)
            DB_HOST=${PROD_DB_HOST:-"localhost"}
            DB_PORT=${PROD_DB_PORT:-"5432"}
            DB_NAME=${PROD_DB_NAME:-"greencode_prod"}
            DB_USER=${PROD_DB_USER:-"postgres"}
            DB_PASSWORD=${PROD_DB_PASSWORD:-"password"}
            ;;
    esac
    
    echo "Database: $DB_NAME on $DB_HOST:$DB_PORT"
}

# Create backup filename
create_backup_filename() {
    local timestamp=$(date +"$DATE_FORMAT")
    local filename="${ENVIRONMENT}_${BACKUP_TYPE}_${timestamp}"
    
    case $BACKUP_TYPE in
        full)
            filename="${filename}.sql"
            ;;
        schema)
            filename="${filename}_schema.sql"
            ;;
        data)
            filename="${filename}_data.sql"
            ;;
    esac
    
    echo "$filename"
}

# Create PostgreSQL backup
create_postgres_backup() {
    local filename="$1"
    local filepath="$BACKUP_DIR/$ENVIRONMENT/$filename"
    
    echo "🗄️  Creating PostgreSQL backup: $filename"
    
    # Set PGPASSWORD for psql
    export PGPASSWORD="$DB_PASSWORD"
    
    case $BACKUP_TYPE in
        full)
            echo "Creating full backup..."
            pg_dump -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" \
                --verbose --clean --create --if-exists > "$filepath"
            ;;
        schema)
            echo "Creating schema-only backup..."
            pg_dump -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" \
                --verbose --schema-only --no-owner --no-privileges > "$filepath"
            ;;
        data)
            echo "Creating data-only backup..."
            pg_dump -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" \
                --verbose --data-only --disable-triggers > "$filepath"
            ;;
    esac
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✅ Backup created successfully${NC}"
        
        # Compress if requested
        if [ "$COMPRESS" = "true" ]; then
            echo "🗜️  Compressing backup..."
            gzip "$filepath"
            filepath="${filepath}.gz"
            echo -e "${GREEN}✅ Backup compressed: ${filepath##*/}${NC}"
        fi
        
        # Show file size
        local size=$(du -h "$filepath" | cut -f1)
        echo "📊 Backup size: $size"
        
        return 0
    else
        echo -e "${RED}❌ Backup failed${NC}"
        return 1
    fi
}

# Create H2 backup (for development)
create_h2_backup() {
    local filename="$1"
    local filepath="$BACKUP_DIR/$ENVIRONMENT/$filename"
    
    echo "🗄️  Creating H2 backup: $filename"
    
    # For H2, we'll create a copy of the database file
    if [ -f "data/greencode.mv.db" ]; then
        cp "data/greencode.mv.db" "$filepath"
        echo -e "${GREEN}✅ H2 backup created successfully${NC}"
        
        # Compress if requested
        if [ "$COMPRESS" = "true" ]; then
            echo "🗜️  Compressing backup..."
            gzip "$filepath"
            filepath="${filepath}.gz"
            echo -e "${GREEN}✅ Backup compressed: ${filepath##*/}${NC}"
        fi
        
        # Show file size
        local size=$(du -h "$filepath" | cut -f1)
        echo "📊 Backup size: $size"
        
        return 0
    else
        echo -e "${YELLOW}⚠️  H2 database file not found${NC}"
        return 1
    fi
}

# Clean old backups
cleanup_old_backups() {
    echo "🧹 Cleaning up old backups..."
    
    local backup_path="$BACKUP_DIR/$ENVIRONMENT"
    local deleted_count=0
    
    if [ -d "$backup_path" ]; then
        # Find and delete old backup files
        find "$backup_path" -name "*.sql*" -type f -mtime +$RETENTION_DAYS -delete
        
        # Count remaining files
        local remaining_count=$(find "$backup_path" -name "*.sql*" -type f | wc -l)
        echo -e "${GREEN}✅ Cleanup completed. $remaining_count backups remaining${NC}"
    fi
}

# Show backup summary
show_backup_summary() {
    echo ""
    echo "🎉 Backup completed successfully!"
    echo ""
    echo "Backup Information:"
    echo "  - Environment: $ENVIRONMENT"
    echo "  - Type: $BACKUP_TYPE"
    echo "  - Location: $BACKUP_DIR/$ENVIRONMENT/"
    echo "  - Compression: $COMPRESS"
    echo ""
    echo "Recent Backups:"
    ls -la "$BACKUP_DIR/$ENVIRONMENT/" | head -10
    echo ""
    echo "Next steps:"
    echo "  - Verify backup integrity"
    echo "  - Test restore process"
    echo "  - Store backup in secure location"
}

# Main backup function
main() {
    echo "=========================================="
    echo "    GreenCode Database Backup"
    echo "=========================================="
    
    create_backup_directory
    get_database_config
    
    local filename=$(create_backup_filename)
    
    # Choose backup method based on environment
    case $ENVIRONMENT in
        dev)
            if [ "$DB_HOST" = "localhost" ] && [ "$DB_NAME" = "greencode" ]; then
                create_h2_backup "$filename"
            else
                create_postgres_backup "$filename"
            fi
            ;;
        staging|prod)
            create_postgres_backup "$filename"
            ;;
    esac
    
    if [ $? -eq 0 ]; then
        cleanup_old_backups
        show_backup_summary
    else
        echo -e "${RED}❌ Backup failed${NC}"
        exit 1
    fi
}

# Run main function
main "$@"
