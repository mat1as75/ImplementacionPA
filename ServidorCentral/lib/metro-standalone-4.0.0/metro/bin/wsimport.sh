#!/bin/sh
#
# Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Distribution License v. 1.0, which is available at
# http://www.eclipse.org/org/documents/edl-v10.php.
#
# SPDX-License-Identifier: BSD-3-Clause
#

if [ -z "$METRO_HOME" ]
then
    # search the installation directory
    
    PRG=$0
    progname=`basename $0`
    saveddir=`pwd`
    
    cd "`dirname $PRG`"
    
    while [ -h "$PRG" ] ; do
        ls=`ls -ld "$PRG"`
        link=`expr "$ls" : '.*-> \(.*\)$'`
        if expr "$link" : '.*/.*' > /dev/null; then
            PRG="$link"
        else
            PRG="`dirname $PRG`/$link"
        fi
    done

    METRO_HOME=`dirname "$PRG"`/..
    
    # make it fully qualified
    cd "$saveddir"
    METRO_HOME=`cd "$METRO_HOME" && pwd`
    
    cd "$saveddir"
fi

[ `expr \`uname\` : 'CYGWIN'` -eq 6 ] &&
{
    METRO_HOME=`cygpath -w "$METRO_HOME"`
}

#
# use or infer JAVA_HOME
#
if [ -n "$JAVA_HOME" ]
then
    JAVA="$JAVA_HOME"/bin/java
else
    JAVA=java
fi

exec "$JAVA" $WSIMPORT_OPTS -jar "$METRO_HOME/lib/webservices-tools.jar" "$@"
