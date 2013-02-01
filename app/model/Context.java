package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

import org.codehaus.jackson.JsonNode;

import play.db.ebean.*;


/*
Class to keep the info about Steps, Data, Status of a workflow
 */
public class Context extends Model{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5168079206157405364L;
	AtomicLong counter = new AtomicLong();
    protected String transactionId;
    private String callerServiceId;
    private String manifestId;

	private Step currentStep;
    private StepStatus currentStepStatus = StepStatus.PENDING;
    private JsonNode data = null;

    /**
     * Data object provided as input to each transaction step.
     * Should be set before the step is processed.
     */
    private Object requestObj;

    /**
     * Result object set by each transaction step.
     * Data that needs to be passed on the subsequent steps goes here.
     */
    private Object resultObj;


    /**
     * Used for transaction id generation
     */
    private DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmssSSS");

    /**
     * Generates and sets transaction id for the work flow/transaction.
     *
     * @param serviceId
     */
    public Context(JsonNode request) {
    	System.out.println("in context cons");
        this.callerServiceId = request.get("caller_service_id").toString();
        setData(request.get("request_params"));
        setManifestId(request.get("manifest_id").toString());
        generateTransactionId();
        System.out.println("done");
    }

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}
	
    public String getManifestId() {
		return manifestId;
	}

	public void setManifestId(String manifestId) {
		this.manifestId = manifestId;
	}

	/**
     * Abstract method to be implemented by the use case data/context.
     * Gets the next step to be executed in the transaction.
     * Transitional logic has to be defined here for each use case.
     *
     * @return
     */
    public  Step getNextStep() {
		return currentStep;    	
    }

    /**
     * Generates unique transaction id
     * Format: Product id + Peer Id + date + AtomicInteger counter
     * eg: DeviceddMMyyyyHHmmssSSS1
     */
    private void generateTransactionId() {
        java.util.Date date = new java.util.Date();
        setTransactionId(callerServiceId + dateFormat.format(date) + counter.incrementAndGet());
    }

    public String getTransactionId() {
        return transactionId;
    }

    private void setTransactionId(String txnId) {
        this.transactionId = txnId;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

    public Object getRequestObj() {
        return requestObj;
    }

    public void setRequestObj(Object reqObj) {
        this.requestObj = reqObj;
    }

    public void setCurrentStep(Step currentStep) {
        this.currentStep = currentStep;
    }

    public Step getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStepStatus(StepStatus currentStepStatus) {
        this.currentStepStatus = currentStepStatus;
    }

    public StepStatus getCurrentStepStatus() {
        return currentStepStatus;
    }

	@Override
	public String toString() {
		return "Context [transactionId=" + transactionId + ", callerServiceId="
				+ callerServiceId + ", manifestId=" + manifestId
				+ ", currentStep=" + currentStep + ", currentStepStatus="
				+ currentStepStatus + ", data=" + data + ", requestObj="
				+ requestObj + ", resultObj=" + resultObj + "]";
	}


}