package com.mygaming3033.beastdiscordutility.utils;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

public enum MinecraftVersion {
    UNKNOWN(2147483647),
    MC1_7_R4(174),
    MC1_8_R3(183),
    MC1_9_R1(191),
    MC1_9_R2(192),
    MC1_10_R1(1101),
    MC1_11_R1(1111),
    MC1_12_R1(1121),
    MC1_13_R1(1131),
    MC1_13_R2(1132),
    MC1_14_R1(1141),
    MC1_15_R1(1151),
    MC1_16_R1(1161),
    MC1_16_R2(1162),
    MC1_16_R3(1163),
    MC1_17_R1(1171),
    MC1_18_R1(1181, true);

    private static MinecraftVersion version;

    private static Boolean hasGsonSupport;

    private static boolean bStatsDisabled;

    private static boolean disablePackageWarning;

    private static boolean updateCheckDisabled;

    private static Logger logger;

    protected static final String VERSION = "1.0";

    private final int versionId;

    private final boolean mojangMapping;

    static {
        bStatsDisabled = false;
        disablePackageWarning = false;
        updateCheckDisabled = false;
        logger = Logger.getLogger("BeastHubUtilities");
    }

    MinecraftVersion(int versionId) {
        this.versionId = versionId;
        this.mojangMapping = false;


    }
    MinecraftVersion(int versionId, boolean mojangMapping) {
        this.versionId = versionId;
        this.mojangMapping = mojangMapping;
    }

    public int getVersionId() {
        return this.versionId;
    }

    public boolean isMojangMapping() {
        return this.mojangMapping;
    }

    public String getPackageName() {
        if (this == UNKNOWN)
            return values()[(values()).length - 1].name().replace("MC", "v");
        return name().replace("MC", "v");
    }

    public static boolean isAtLeastVersion(MinecraftVersion version) {
        return (getVersion().getVersionId() >= version.getVersionId());
    }

    public static boolean isNewerThan(MinecraftVersion version) {
        return (getVersion().getVersionId() > version.getVersionId());
    }

    public static MinecraftVersion getVersion() {
        if (version != null)
            return version;
        String ver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        logger.info("Found Spigot: " + ver + "! Trying to find NMS support");
        try {
            version = valueOf(ver.replace("v", "MC"));
        } catch (IllegalArgumentException ex) {
            version = UNKNOWN;
        }
        if (version != UNKNOWN) {
            logger.info("NMS support '" + version.name() + "' loaded!");
        } else {
            logger.warning(" Wasn't able to find NMS Support! Some functions may not work!");
        }
        init();
        return version;
    }

    private static void init() {


        String defaultPackage = new String(new byte[] {
                100, 101, 46, 116, 114, 55, 122, 119, 46, 99,
                104, 97, 110, 103, 101, 109, 101, 46, 110, 98,
                116, 97, 112, 105, 46, 117, 116, 105, 108, 115 });
    }

    public static boolean hasGsonSupport() {
        if (hasGsonSupport != null)
            return hasGsonSupport.booleanValue();
        try {
            logger.info(" Found Gson: " + Class.forName("com.google.gson.Gson"));
            hasGsonSupport = Boolean.valueOf(true);
        } catch (Exception ex) {
            logger.info(" Gson not found! This will not allow the usage of some methods!");
            hasGsonSupport = Boolean.valueOf(false);
        }
        return hasGsonSupport.booleanValue();
    }

    public static void disableBStats() {
        bStatsDisabled = true;
    }

    public static void disableUpdateCheck() {
        updateCheckDisabled = true;
    }

    public static void disablePackageWarning() {
        disablePackageWarning = true;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void replaceLogger(Logger logger) {
        if (logger == null)
            throw new NullPointerException("Logger can not be null!");
        MinecraftVersion.logger = logger;
    }
}
