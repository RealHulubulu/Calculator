
public abstract class Operation {

    private double resultOfOperation;
    public double operationAnswer = 0;

    protected Operation() {
    }

    public Operation(double input1, double input2, char operationChar) {

        switch (operationChar) {
            case '+':
                resultOfOperation = input1 + input2;
                break;
            case '-':
                resultOfOperation = input1 - input2;
                break;
            case '*':
                resultOfOperation = input1 * input2;
                break;
            case '/':
                resultOfOperation = input1 / input2;
                break;
        }

        System.out.println("The result is: " + resultOfOperation);
    }

    public Double getResultOfOperation() {
        return resultOfOperation;
    }

    abstract void doOperation(double input1, double input2);


}
