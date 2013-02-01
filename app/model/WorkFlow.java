package model;

import java.util.HashMap;

import org.codehaus.jackson.JsonNode;

public class WorkFlow {

    private Context context = null;

    public WorkFlow(JsonNode request) {
        setContext(new Context(request));
    }

	/**
     * Executes all the steps which form part of the transaction/work flow. The
     * use case data object/context object provides the next step to be
     * executed. It is the responsibility of the use case to handle Exceptions
     * thrown by the Workflow.
     *
     * @throws Exception
     */
    public void execute(Context context) throws Exception {
        Step currentStep;
        currentStep = context.getNextStep();
        while (currentStep != null) {
            currentStep.processStep(context);
            if(context.getCurrentStepStatus().equals(StepStatus.PAUSED)) {
                return;
            }
            currentStep = context.getNextStep();
        }
    }

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
