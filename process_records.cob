      * *****************************************************************
      * Program name:    MYPROG
      * Original author: Anna Olson
      *
      * Maintenance Log
      * Date      Author        Maintenance Requirement
      * --------- ------------ ---------------------------------------
      * 06/15/26 Anna Olson    Created for COBOL learning
      * *****************************************************************

       IDENTIFICATION DIVISION.
       PROGRAM-ID. MYPROG.
       AUTHOR. Anna Olson.
       DATE-WRITTEN. 06/14/26.
       DATE-COMPILED. 06/14/26.

      * *****************************************************************

       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SOURCE-COMPUTER. IBM-3081.
       OBJECT-COMPUTER. IBM-3081.
      * *****************************************************************

       DATA DIVISION.
       WORKING-STORAGE SECTION. 
       01 ws-name PIC A(30) VALUE 'anna olson'.
       01 ws-phone PIC 9(10) VALUE '2535146144'.
       01 ws-voucher PIC x(5) VALUE 'A1234'.
      * *****************************************************************

       PROCEDURE DIVISION.
           DISPLAY ws-name.
           STOP RUN.

      * *****************************************************************
