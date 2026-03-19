#!/bin/sh
# -----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# -----------------------------------------------------------------------------

# -----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.2.0
#
# Required ENV vars:
# JAVA_HOME - location of a JDK home dir
#
# -----------------------------------------------------------------------------

set -euf
[ "${MVNW_VERBOSE-}" != true ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
  CYGWIN* | MINGW*)
    [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
    native_path() { cygpath --path --windows "$1"; }
    ;;
esac

# set JAVACMD and JAVA_HOME
if [ -z "${JAVA_HOME-}" ] ; then
  JAVACMD=java
  which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
else
  JAVACMD="$JAVA_HOME/bin/java"
  if [ ! -x "$JAVACMD" ] ; then
    die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
  fi
fi

[ -z "$JAVA_HOME" ] && die "JAVA_HOME not set"
[ -x "$JAVA_HOME/bin/java" ] || die "JAVA_HOME is not a valid java home directory"

# Find the project base dir
cwd="$(pwd)"
MAVEN_PROJECTBASEDIR="${MAVEN_BASEDIR:-$cwd}"

# Look for the --file switch and use the directory of that file as MAVEN_PROJECTBASEDIR
# This is a workaround for the case where the mvnw is called from a subdirectory
# of the project.
if [ -n "${MAVEN_PROJECTBASEDIR:-}" ] ; then
  while [ "$MAVEN_PROJECTBASEDIR" != "/" ] ; do
    if [ -f "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties" ] ; then
      break
    fi
    MAVEN_PROJECTBASEDIR="$(dirname "$MAVEN_PROJECTBASEDIR")"
  done
fi

# Download the wrapper jar if it doesn't exist
WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
if [ ! -f "$WRAPPER_JAR" ] ; then
  WRAPPER_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
  printf "Downloading %s\n" "$WRAPPER_URL"
  if command -v wget >/dev/null 2>&1; then
    wget -q -O "$WRAPPER_JAR" "$WRAPPER_URL" || rm -f "$WRAPPER_JAR"
  elif command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$WRAPPER_JAR" "$WRAPPER_URL" || rm -f "$WRAPPER_JAR"
  else
    printf "Could not find wget or curl to download %s\n" "$WRAPPER_URL"
  fi
fi

if [ ! -f "$WRAPPER_JAR" ] ; then
  die "ERROR: Could not find or download the Maven wrapper jar"
fi

# JVM config
JVM_CONFIG_MAVEN_PROPS=""
if [ -f "$MAVEN_PROJECTBASEDIR/.mvn/jvm.config" ] ; then
  JVM_CONFIG_MAVEN_PROPS="$(cat "$MAVEN_PROJECTBASEDIR/.mvn/jvm.config")"
fi

# Execute the wrapper
exec "$JAVA_HOME/bin/java" $JVM_CONFIG_MAVEN_PROPS $MAVEN_OPTS $MAVEN_DEBUG_OPTS \
  -classpath "$WRAPPER_JAR" \
  "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECTBASEDIR}" \
  org.apache.maven.wrapper.MavenWrapperMain "$@"
