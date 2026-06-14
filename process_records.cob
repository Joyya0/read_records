      *****************************************************************
      * Program name:    MYPROG                               
      * Original author: MYNAME                                
      *
      * Maintenence Log                                              
      * Date      Author        Maintenance Requirement               
      * --------- ------------  --------------------------------------- 
      * 01/01/08 MYNAME  Created for COBOL class         
      *                                                               
      *****************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID.  MYPROG.
       AUTHOR. MYNAME. 
       INSTALLATION. COBOL DEVELOPMENT CENTER. 
       DATE-WRITTEN. 01/01/08. 
       DATE-COMPILED. 01/01/08. 
       SECURITY. NON-CONFIDENTIAL.
      *****************************************************************
      *****************************************************************
       ENVIRONMENT DIVISION. 
       CONFIGURATION SECTION. 
       SOURCE-COMPUTER. IBM-3081. 
       OBJECT-COMPUTER. IBM-3081. 
       INPUT-OUTPUT SECTION.
       FILE-CONTROL. 
           SELECT INFILE ASSIGN  TO INFILEDD.
           SELECT OUTFILE ASSIGN TO OUTFILEDD.
      *****************************************************************
      *****************************************************************
       DATA DIVISION.
       FILE SECTION. 
       FD  File1
            LABEL RECORDS ARE STANDARD       
            DATA RECORD IS RecName      
            RECORD CONTAINS RecordLength CHARACTERS
            RECORDING MODE IS F
            BLOCK CONTAINS 0 RECORDS.
       01  Rec1    PIC X(RecordLength).
      *****************************************************************
       WORKING-STORAGE SECTION.
       01  COUNTERS-AND-ACCUMULATORS.
           05  REC-KTR        PIC S9(4)     COMP.
           05  TOTAL-AMOUNT   PIC S9(3)V99  COMP-3.
           05  INPUT-DATA     PIC X(30).
      *****************************************************************
      ******************************************************************
       PROCEDURE DIVISION.
           PERFORM 000-HOUSEKEEPING THRU 000-EXIT.
           PERFORM 100-MAINLINE THRU 100-EXIT
                   UNTIL NO-MORE-RECORDS.
           PERFORM 200-CLEANUP THRU 200-EXIT.
           GOBACK.

      ******************************************************************
      *  This routine should perform file open and an initial read
      ******************************************************************
       000-HOUSEKEEPING.
           DISPLAY "000-HOUSEKEEPING".
      *  Code your statement here to OPEN files
      *     ACCEPT  WS-DATE FROM DATE.
      *     OPEN INPUT INFILE.
      *     OPEN OUTPUT OUTFILE, RPTFILE, ERRFILE.

      *  Code your statement here to read the input file
      *  Remember to move "NO" to IFCODE if the input file is AT END
      *     READ INFILE INTO WS-INPUT-REC
      *         AT END
      *         MOVE 'N' TO MORE-RECORDS-SW
      *         GO TO 000-EXIT
      *     END-READ
      *     INITIALIZE  COUNTERS-AND-ACCUMULATORS,
      *                 WS-OUTPUT-REC,
      *                 WS-TOTALS-REC
      *     ADD +1 TO RECORDS-READ.
       000-EXIT.
           EXIT.

      ******************************************************************
      *  This routine contains the business logic for the program
      ******************************************************************
       100-MAINLINE.
      *  Validate patient type and insurance coverage

      *  Add to counters and total amounts

      *  Move input data to output data
      *      move in-rec TO out-rec.
      *     WRITE RPT-REC FROM WS-OUTPUT-REC.

      *  Code your statements here to read the input file
      *  Remember to move "NO" to IFCODE if the input file is AT END
      *     READ INFILE INTO WS-INPUT-REC
      *         AT END MOVE "N" TO MORE-RECORDS-SW
      *         GO TO 100-EXIT
      *     END-READ
      *     ADD +1 TO RECORDS-READ.
       100-EXIT.
           EXIT.

      ******************************************************************
      *  This routine should perform file close operations
      ******************************************************************
       200-CLEANUP.
      *  Move the final computational fields
           DISPLAY "200-CLEANUP".


      *  Code the statement write the final output record
      *     WRITE RPT-REC FROM WS-TOTALS-REC.
      *  Code the statement to close all files
      *     CLOSE OUTFILE, RPTFILE, ERRFILE, INFILE.
      *  Code the statement to Display a successful end-of-job message
           DISPLAY "NORMAL END OF JOB".
       200-EXIT.
           EXIT.