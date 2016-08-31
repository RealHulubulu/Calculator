
public class SubtractOperation extends Operation{

    public SubtractOperation(){}

    public SubtractOperation( double input1, double input2, char operationChar){

        super( input1, input2, operationChar);

    }

    void doOperation(double input1, double input2){

        operationAnswer = input1 - input2;

    }

    public double getOperationAnswer(){
        return operationAnswer;
    }

}
