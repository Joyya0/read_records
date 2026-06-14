      * *****************************************************************
      * Program name:    MYPROG
      * Original author: Anna Olson
      *
      * Maintenance Log
      * Date      Author        Maintenance Requirement
      * --------- ------------ ---------------------------------------
      * 01/01/08 Anna Olson    Created for COBOL learning
      * *****************************************************************

       IDENTIFICATION DIVISION.
       PROGRAM-ID. MYPROG.
       AUTHOR. Anna Olson.
       DATE-WRITTEN. 06/14/26.
       DATE-COMPILED. 06/14/26.

       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SOURCE-COMPUTER. IBM-3081.
       OBJECT-COMPUTER. IBM-3081.

       DATA DIVISION.

       PROCEDURE DIVISION.
           DISPLAY 'HELLO WORLD'.
           STOP RUN.
           