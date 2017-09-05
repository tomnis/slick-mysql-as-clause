#!/bin/bash

sbt gen-tables
mkdir -pv ../src/main/scala/org/mccandless
cp target/scala-2.12/src_managed/slick/org/mccandless/Tables.scala ../src/main/scala/org/mccandless
