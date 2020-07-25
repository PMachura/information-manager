package machura.przemyslaw.informationmanagerdomain.domain.errors;

public class FaultDefault implements Fault {

    private final String errorReason;

    public FaultDefault(String errorReason) {
        this.errorReason = errorReason;
    }

    public FaultDefault fromReason(String reason){
        return new FaultDefault(reason);
    }

    @Override
    public String getErrorReason(){
        return this.errorReason;
    }
}
