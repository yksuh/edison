#include<stdio.h>
#include<stdlib.h>

//const char* resultFileDir = "result";
const char* resultFileName = "output.txt";

int main(int argc, char* argv[]){
    char operator;
    int num1, num2;
    
    FILE* fp = fopen(argv[1], "r");
    if(fp == NULL){
	fprintf(stderr, "Cannot open the input file %s\n", argv[1]);
	return -1;
    }
    //fprintf(stdout, "success!\n");
    fscanf(fp, "%c %d %d", &operator, &num1, &num2);
    //fprintf(stdout, "input: %c, %d, %d\n", operator, num1, num2);

    FILE* fp_res = fopen("./result/output.txt", "w");
    if(fp_res == NULL){
	fprintf(stderr, "Cannot open the output file %s\n", resultFileName);
	return -1;
    }
    
    switch(operator) {
        case '+':
            fprintf(fp_res, "%d", num1+num2);
            break;
        case '-':
            fprintf(fp_res, "%d", num1-num2);
            break;
        case '*':
            fprintf(fp_res, "%d", num1*num2);
            break;
        case '/':
	    if(num2 == 0) 
            	fprintf(fp_res, "Division by zero");
	    else
		fprintf(fp_res, "%.2f", num1, num2, (double)num1/(double)num2);
            break;
        default:
            /* If operator is other than +, -, * or /, error message is shown */
            fprintf(fp_res, "Error! operator is not correct");
            break;
    }
    fclose(fp);
    fclose(fp_res);
    return 0;
}
