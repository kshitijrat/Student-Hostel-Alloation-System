package com.kshitijhms.hms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitijhms.hms.entities.Visited;
import com.kshitijhms.hms.entities.VisitorIn;
import com.kshitijhms.hms.repo.VisitedRepo;

@Service
public class VisitedService {

    @Autowired
    VisitedRepo vrepo;

    public boolean isVisited(VisitorIn visitorIn) {
        List<Visited> list = vrepo.findAll();
        for (Visited v : list) {
            if (v.getVisitor().equals(visitorIn)) {
                return true;
            }
        }
        return false;
    }

}
