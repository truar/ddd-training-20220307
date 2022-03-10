package com.zenika.training.ddd.pcscol.domain.registrationApplication;

public class Attachment {
    private final String url;
    private final AttachmentType type;

    public Attachment(String url, AttachmentType type) {
        this.url = url;
        this.type = type;
    }

    public AttachmentType getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
