@REM -----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM -----------------------------------------------------------------------------

@REM -----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.2.0
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REM
@REM -----------------------------------------------------------------------------

@setlocal

@set ERROR_CODE=0

@REM ==== START VALIDATION ====
if not "%JAVA_HOME%" == "" goto OkJHome

echo.
echo Error: JAVA_HOME not found in your environment. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

:OkJHome
if exist "%JAVA_HOME%\bin\java.exe" goto chkMHome

echo.
echo Error: JAVA_HOME is set to an invalid directory. >&2
echo JAVA_HOME = "%JAVA_HOME%" >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

:chkMHome
set "M2_HOME=%~dp0.mvn"

if not "%M2_HOME%" == "" goto valMHome

echo.
echo Error: M2_HOME is not set. >&2
echo Please set the M2_HOME variable in your environment to match the >&2
echo location of your Maven installation. >&2
echo.
goto error

:valMHome

:init

set "MAVEN_CMD_LINE_ARGS=%*"

@REM Find the project base dir, i.e. the directory that contains the folder ".mvn".
@REM Fallback to current working directory if not found.

set "MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%"
if not "%MAVEN_PROJECTBASEDIR%"=="" goto endDetectBaseDir

set "EXEC_DIR=%CD%"
set "WDIR=%EXEC_DIR%"

@REM Look for the --file switch and use the directory of that file as MAVEN_PROJECTBASEDIR
@REM This is a workaround for the case where the mvnw is called from a subdirectory
@REM of the project.
:loop
if "%WDIR%" == "%WDIR:~0,1%" goto endDetectBaseDir
if exist "%WDIR%\.mvn" goto endDetectBaseDir
cd /d "%WDIR%"
cd /d ".."
set "WDIR=%CD%"
goto loop

:endDetectBaseDir
set "MAVEN_PROJECTBASEDIR=%WDIR%"
cd /d "%EXEC_DIR%"

set "jvmConfig=%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config"
if not exist "%jvmConfig%" goto endReadJvmConfig

@REM Read the JVM config and export it to MAVEN_OPTS
@REM The first line starting with # is treated as a comment
@REM The rest is appended to MAVEN_OPTS
for /F "usebackq delims=" %%a in ("%jvmConfig%") do set "JVM_CONFIG_MAVEN_PROPS=!JVM_CONFIG_MAVEN_PROPS! %%a"

:endReadJvmConfig

set "MAVEN_JAVA_EXE=%JAVA_HOME%\bin\java.exe"
set "WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set "WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain"

set "WRAPPER_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"

if exist "%WRAPPER_JAR%" goto runWrapper

@REM Download the wrapper jar if it doesn't exist

echo Downloading %WRAPPER_URL%
powershell -Command "& {trap{[System.Console]::Error.Write($_.Exception.Message);exit 1};(New-Object System.Net.WebClient).DownloadFile('%WRAPPER_URL%','%WRAPPER_JAR%');}"
if "%ERRORLEVEL%" == "0" goto runWrapper
echo Couldn't download %WRAPPER_URL%
goto error

:runWrapper
set "MAVEN_CMD_LINE_ARGS=%MAVEN_CMD_LINE_ARGS%"

"%MAVEN_JAVA_EXE%" %JVM_CONFIG_MAVEN_PROPS% %MAVEN_OPTS% %MAVEN_DEBUG_OPTS% -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" %WRAPPER_LAUNCHER% %MAVEN_CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%

if not "%MAVEN_SKIP_RC%" == "" goto skipRcPost
@REM check for post script, once with legacy .bat ending and once with .cmd ending
if exist "%USERPROFILE%\mavenrc_post.bat" call "%USERPROFILE%\mavenrc_post.bat"
if exist "%USERPROFILE%\mavenrc_post.cmd" call "%USERPROFILE%\mavenrc_post.cmd"
:skipRcPost

exit /B %ERROR_CODE%
