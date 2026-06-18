#!/bin/bash

echo "🔄 Restarting GreenCode..."

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

"$SCRIPT_DIR/stop.sh"
sleep 2
"$SCRIPT_DIR/start.sh"

echo "✅ Restart complete!"
