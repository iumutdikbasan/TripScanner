package com.iumutdikbasan.tripSearch.business.scheduledTasks;

import com.iumutdikbasan.tripSearch.business.abstracts.StationBusiness;
import com.iumutdikbasan.tripSearch.business.abstracts.TripBusiness;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FetchDatasFromMockApi {

    @Autowired
    private TripBusiness tripBusiness;

    @Scheduled(cron="0 0 18 * * ?")
    public void fetchDataAndSave() {
        this.tripBusiness.fetchFromMockApi();
    }
}
