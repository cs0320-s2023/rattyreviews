package edu.brown.cs.student.main.scheduled;

import edu.brown.cs.student.main.APIHandlers.ProvideMenu;
import edu.brown.cs.student.main.Utils.reviewController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

public class SchedScripts {
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void updateMenu() {
        Runnable fileUpdater = () -> {
            LocalDate current = LocalDate.now(ZoneId.of("UTC-04:00"));
            new ProvideMenu().GetMenuForDate(current);
            System.out.println("updated menu " + Calendar.getInstance().getTime());
        };
        ScheduledFuture<?> fileHandler =
                scheduler.scheduleAtFixedRate(fileUpdater, 0, 5, MINUTES);
        Runnable canceller = () -> fileHandler.cancel(false);
        //scheduler.schedule(canceller, 30, DAYS);
    }

    public void storeNewReviews(reviewController rc) {
        Runnable fileUpdater = () -> {
            rc.addToStorage();
            System.out.println("stored reviews " + Calendar.getInstance().getTime());
        };
        ScheduledFuture<?> fileHandler =
                scheduler.scheduleAtFixedRate(fileUpdater, 1, 20, SECONDS);
        Runnable canceller = () -> fileHandler.cancel(false);
        //scheduler.schedule(canceller, 30, DAYS);
    }
}
