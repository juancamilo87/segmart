@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Todos los derechos reservados 2005
REM
REM Proyecto Cupi2
REM Ejercicio: n12_almacen
REM Autor: Mario S�nchez - 06-nov-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

java -classpath ../lib/almacen.jar;../test/lib/almacenTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.almacen.test.AlmacenTest