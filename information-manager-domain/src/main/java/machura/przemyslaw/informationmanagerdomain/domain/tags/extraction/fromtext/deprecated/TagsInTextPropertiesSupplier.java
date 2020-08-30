package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.deprecated;

interface TagsInTextPropertiesSupplier {
    String getAttributesNameValueSeparator();
    String getStartMarker();
    String getEndMarker();
    String getTerminationMarker();
    String getAttributesValueEnclosingMarker();
}
