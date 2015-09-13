package com.github.qrokodial.sparkle.utilities.versioning;

public class Version {
    private int major;
    private int minor;
    private int revision;
    private Stage stage;

    public enum Stage {
        /**
         * For final releases of the particular version.
         */
        RELEASE,

        /**
         * A release candidate. You think this *might* be suitable for a final release, but you still want to do more
         * testing.
         */
        RC,

        /**
         * A beta release. This typically means you have all your features in and you're just working on bug fixes.
         */
        BETA,

        /**
         * Alpha releases are for early on in development where you still have things to do.
         */
        ALPHA,

        /**
         * When you have so much left to do and so much left to change you feel funny calling it an alpha release.
         */
        GAMMA;
    }

    /**
     * Instantiates the class.
     *
     * @param major
     * @param minor
     * @param revision
     * @param stage
     */
    public Version(int major, int minor, int revision, Stage stage) {
        this.major = major;
        this.minor = minor;
        this.revision = revision;
        this.stage = stage;
    }

    /**
     * @return the major version number
     */
    public int getMajor() {
        return major;
    }

    /**
     * @return the minor version number
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @return the revision version number
     */
    public int getRevision() {
        return revision;
    }

    /**
     * @return the stage of the version
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @return the string representation of the class
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getMajor());
        builder.append(".");
        builder.append(getMinor());
        builder.append(".");
        builder.append(getRevision());

        if (!getStage().equals(Stage.RELEASE)) {
            builder.append(" ");
            builder.append(getStage());
        }

        return builder.toString();
    }
}
