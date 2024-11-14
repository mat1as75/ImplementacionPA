@echo off

REM
REM  Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
REM
REM  This program and the accompanying materials are made available under the
REM  terms of the Eclipse Distribution License v. 1.0, which is available at
REM  http://www.eclipse.org/org/documents/edl-v10.php.
REM
REM  SPDX-License-Identifier: BSD-3-Clause
REM

rem
rem Infer METRO_HOME if not set
rem
if not "%METRO_HOME%" == "" goto CHECKJAVAHOME

rem Try to locate METRO_HOME
set METRO_HOME=%~dp0
set METRO_HOME=%METRO_HOME%\..
if exist %METRO_HOME%\lib\webservices-tools.jar goto CHECKJAVAHOME

rem Unable to find it
echo METRO_HOME must be set before running this script
goto END

:CHECKJAVAHOME
if not "%JAVA_HOME%" == "" goto USE_JAVA_HOME

set JAVA=java
goto LAUNCH

:USE_JAVA_HOME
set JAVA="%JAVA_HOME%\bin\java"
goto LAUNCH

:LAUNCH
%JAVA% %WSGEN_OPTS% -cp "%METRO_HOME%\lib\webservices-tools.jar" com.sun.tools.ws.WsGen %*

:END
%COMSPEC% /C exit %ERRORLEVEL%
