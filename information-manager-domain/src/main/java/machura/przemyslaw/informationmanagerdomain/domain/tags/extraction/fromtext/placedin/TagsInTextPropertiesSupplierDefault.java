package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.placedin;

import lombok.Builder;

@Builder(builderClassName = "Builder")
class TagsInTextPropertiesSupplierDefault implements TagsInTextPropertiesSupplier {

    public final String startMarker;
    public final String endMarker;
    public final String terminationMarker;
    public final String attributesNameValueSeparator;
    public final String attributesValuesEnclosingMarker;

    @Override
    public String getStartMarker() {
        return startMarker;
    }

    @Override
    public String getEndMarker() {
        return endMarker;
    }

    @Override
    public String getTerminationMarker() {
        return terminationMarker;
    }

    @Override
    public String getAttributesNameValueSeparator() {
        return attributesNameValueSeparator;
    }

    @Override
    public String getAttributesValueEnclosingMarker() {
        return attributesValuesEnclosingMarker;
    }
}
