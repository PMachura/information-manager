package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.placedin;

interface TagsInTextPropertiesSupplier {
    String getAttributesNameValueSeparator();
    String getStartMarker();
    String getEndMarker();
    String getTerminationMarker();
    String getAttributesValueEnclosingMarker();
}
