//Code example to add to main
//AddOperation addOperation = new AddOperation();
//addOperation.doOperation(userInput1,userInput2);
//System.out.println("Answer is: " + addOperation.getOperationAnswer());

public class AddOperation extends Operation{

    public AddOperation(){}

    public AddOperation( double input1, double input2, char operationChar){

       super( input1, input2, operationChar);

    }

    void doOperation(double input1, double input2){

        operationAnswer = input1 + input2;

    }

    public double getOperationAnswer(){
        return operationAnswer;
    }

}
