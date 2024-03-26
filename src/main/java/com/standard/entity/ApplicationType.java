package com.standard.entity;

public enum ApplicationType {


    D("DESKTOP"),
    M("MOBILE");

    private final String id;

    ApplicationType(String id) {
        this.id = id;
    }

    public static ApplicationType fromId(String applicationId) {
        if (applicationId != null)
            for (ApplicationType item : ApplicationType.values())
                if (applicationId.equals(item.toString()))
                    return item;
        return null;
    }

    public static ApplicationType fromName(String name) {
        if (name != null)
            for (ApplicationType item : ApplicationType.values())
                if (name.equals(item.name()))
                    return item;
        return null;
    }

    public String getId() {
        return this.id;
    }
    }
