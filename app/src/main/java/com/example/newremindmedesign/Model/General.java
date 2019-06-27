package com.example.newremindmedesign.Model;

public class General {

    private long id, subProviderId;
    private String name, bankLink, subProviderName, icon,subProviderIcon;

    public General() {
    }

    public General(long id, String icon, long subProviderId, String subProviderIcon, String name, String subProviderName) {
        this.id = id;
        this.icon = icon;
        this.subProviderId = subProviderId;
        this.subProviderIcon = subProviderIcon;
        this.name = name;
        this.subProviderName = subProviderName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubProviderId() {
        return subProviderId;
    }

    public void setSubProviderId(long subProviderId) {
        this.subProviderId = subProviderId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSubProviderIcon() {
        return subProviderIcon;
    }

    public void setSubProviderIcon(String subProviderIcon) {
        this.subProviderIcon = subProviderIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankLink() {
        return bankLink;
    }

    public void setBankLink(String bankLink) {
        this.bankLink = bankLink;
    }

    public String getSubProviderName() {
        return subProviderName;
    }

    public void setSubProviderName(String subProviderName) {
        this.subProviderName = subProviderName;
    }
}
