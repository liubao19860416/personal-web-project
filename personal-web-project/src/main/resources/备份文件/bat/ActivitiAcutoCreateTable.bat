@echo off
title Activiti5-CreateTableProcess
color a
@echo Activiti5���ݿ⵼����...

mysql -uroot -pmikesirius activiti < activiti.mysql.create.engine.sql
mysql -uroot -pmikesirius activiti < activiti.mysql.create.history.sql
mysql -uroot -pmikesirius activiti < activiti.mysql.create.identity.sql

@rem pause

if "%OS%"=="Windows_NT" ENDLOCAL
