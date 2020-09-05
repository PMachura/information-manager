package machura.przemyslaw.informationmanagerdomain.domain.errors;


import java.util.Collection;
import java.util.stream.Collectors;


public class FaultImpl implements Fault {

    private final String errorReason;

    private FaultImpl(String errorReason) {
        this.errorReason = errorReason;
    }

    public static Fault fromReason(String reason) {
        return new FaultImpl(reason);
    }

    public static Fault empty() {
        return new FaultImpl("");
    }

    public static Fault fromFaults(Collection<? extends Fault> faults) {
        return fromReason(faults.stream()
                .map(Fault::getErrorReason)
                .collect(Collectors.joining()));
    }

    @Override
    public String getErrorReason() {
        return this.errorReason;
    }
}
