package ru.kartel.conditionalapp.systemprofile;

public class DevProfile  implements SystemProfile{
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}