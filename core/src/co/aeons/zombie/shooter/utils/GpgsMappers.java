package co.aeons.zombie.shooter.utils;

/**
 * Created by Torstein on 4/12/2018.
 */

import co.aeons.zombie.shooter.ZombieShooter;

/**
 * Created by Benjamin Schulte on 03.07.2017.
 */

public class GpgsMappers {
    public static String mapToGpgsLeaderboard(String leaderboardId) {
        String gpgsId = null;

        if (leaderboardId != null) {
            if (leaderboardId.equals(ZombieShooter.LEADERBOARD1))
                gpgsId = "CgkIu46Sr-8fEAIQAw";
        }

        return gpgsId;
    }

    public static String mapToGpgsAchievement(String achievementId) {
        String gpgsId = null;

        if (achievementId != null) {
            if (achievementId.equals(ZombieShooter.ACHIEVEMENT1))
                gpgsId = "CgkIu46Sr-8fEAIQAg";
        }

        return gpgsId;
    }

    public static String mapToGpgsEvent(String eventId) {
        String gpgsId = null;

        if (eventId != null) {
            if (eventId.equals(ZombieShooter.EVENT1))
                gpgsId = "CgkIu46Sr-8fEAIQAQ";
        }

        return gpgsId;
    }
}