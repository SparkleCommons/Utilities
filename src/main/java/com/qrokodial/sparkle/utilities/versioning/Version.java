package com.qrokodial.sparkle.utilities.versioning;

import java.util.Optional;

public class Version {
    private int major;
    private int minor;
    private int revision;
    private int build;
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

        /**
         * Attempts to get the stage from a string.
         *
         * @param string the string
         * @return the stage, if successful
         */
        public static Optional<Stage> fromString(String string) {
            return Optional.ofNullable(valueOf(string.toUpperCase()));
        }
    }

    /**
     * Instantiates the class.
     *
     * @param major
     * @param minor
     * @param revision
     * @param stage
     */
    public Version(int major, int minor, int revision, int build, Stage stage) {
        this.major = major;
        this.minor = minor;
        this.revision = revision;
        this.stage = stage;
        this.build = build;
    }

    /**
     * Instantiates the class, initializing the build to 0.
     *
     * @param major
     * @param minor
     * @param revision
     * @param stage
     */
    public Version(int major, int minor, int revision, Stage stage) {
        this(major, minor, revision, 0, stage);
    }

    /**
     * Instantiates the class, initializing the build to 0 and the stage to {@link Stage#RELEASE}.
     *
     * @param major
     * @param minor
     * @param revision
     */
    public Version(int major, int minor, int revision) {
        this(major, minor, revision, Stage.RELEASE);
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
     * @return the build version number
     */
    public int getBuild() {
        return build;
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

        if (getBuild() != 0) {
            builder.append(" Build ");
            builder.append(getBuild());
        }

        return builder.toString();
    }

    /**
     * Attempts to convert a string into a {@link Version}.
     *
     * @param version the string
     * @return the version, if successful
     */
    public static Optional<Version> fromString(String version) {
        String[] split = version.toLowerCase().split(" ");

        if (split.length < 1 || split.length > 4) {
            return Optional.empty();
        }

        String[] numbers = split[0].split("\\.");

        int major;
        int minor;
        int revision = 0;

        if (numbers.length < 2 || numbers.length > 3) {
            return Optional.empty();
        }

        try {
            major = Integer.parseInt(numbers[0]);
            minor = Integer.parseInt(numbers[1]);
            if (numbers.length == 3) {
                revision = Integer.parseInt(numbers[2]);
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

        Optional<Stage> stage;

        if (split.length == 1) {
            stage = Optional.of(Stage.RELEASE);
        } else {
            stage = Stage.fromString(split[1]);
        }

        int build = 0;

        if (stage.isPresent() && split.length == 4) {
            if (!split[2].contentEquals("build")) {
                return Optional.empty();
            }

            try {
                build = Integer.parseInt(split[3]);
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        } else if (stage.isPresent() && split.length == 3) {
            try {
                build = Integer.parseInt(split[2]);
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return Optional.of(new Version(major, minor, revision, build, stage.get()));
    }
}
