package pl.com.bottega.cinemasystem.api;

public class CollectPaymentResponse {

    private boolean success;
    private String failureReason;

    public CollectPaymentResponse() {
        this.success = true;
    }

    public CollectPaymentResponse(String failureReason) {
        this.failureReason = failureReason;
        success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    @Override
    public String toString() {
        return "CollectPaymentResponse{" +
                "success=" + success +
                ", failureReason='" + failureReason + '\'' +
                '}';
    }
}
