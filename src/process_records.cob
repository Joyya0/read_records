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
       INPUT-OUTPUT SECTION. 
       FILE-CONTROL. 
           SELECT INPUT-FILE
           ASSIGN TO "request.txt"
           ORGANIZATION IS LINE SEQUENTIAL.

      * *****************************************************************

       DATA DIVISION.

       FILE SECTION.
       FD INPUT-FILE
           RECORDING MODE IS F
           RECORD CONTAINS 80 CHARACTERS 
           BLOCK CONTAINS 0 RECORDS 
           LABEL RECORDS ARE STANDARD 
           DATA RECORD IS STUDENT-REC.

       01 STUDENT-REC.
           05 NAME         PIC A(20).
           05 COURSE       PIC X(07).
           05 CREDITS      PIC 9(01).
       
       
       WORKING-STORAGE SECTION.
       
       01 WS-FINISHED PIC X VALUE "N".
           88 END-OF-FILE VALUE "Y".
       
       01 TOTALCREDITS PIC 99 VALUE 0.
       01 UPCREDITS PIC 99 VALUE 0.

      * *****************************************************************

       PROCEDURE DIVISION.
       
       MAIN.

           OPEN INPUT INPUT-FILE
       
           PERFORM UNTIL END-OF-FILE
       
               READ INPUT-FILE
       
                   AT END
                       SET END-OF-FILE TO TRUE
       
                   NOT AT END
       
                       ADD CREDITS TO TOTALCREDITS
       
                       MOVE TOTALCREDITS TO UPCREDITS
       
                       DISPLAY NAME
                       DISPLAY COURSE
                       DISPLAY "TOTAL: " UPCREDITS
       
                       IF UPCREDITS > 20
                           DISPLAY 'ENROLLMENT OVERLOADED,'
                           ' 20 CREDITS MAXIMUM'
                       END-IF
       
               END-READ
       
           END-PERFORM

           CLOSE INPUT-FILE
           STOP RUN.

      * *****************************************************************
