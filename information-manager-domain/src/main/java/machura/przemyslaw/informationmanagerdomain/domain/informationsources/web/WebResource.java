package machura.przemyslaw.informationmanagerdomain.domain.informationsources.web;

public interface WebResource {
    enum WebResourceType {
        VIDEO_PORTAL;
    }

    enum WebDomain{
        YOUTUBE("youtube.com", WebResourceType.VIDEO_PORTAL);

        private final String uri;
        private final WebResourceType webResourceType;

        WebDomain(String uri, WebResourceType webResourceType) {
            this.uri = uri;
            this.webResourceType = webResourceType;
        }

        public String getUri(){
            return this.uri;
        }

        public WebResourceType getWebResourceType(){
            return this.getWebResourceType();
        }
    }

}
