package com.iwantrun.core.service.application.transfer;

import com.iwantrun.core.service.application.domain.LocationAttachments;
import com.iwantrun.core.service.application.domain.Locations;

public class MixedLocations {
	
	private Locations locations ;
	
	private LocationAttachments attach ;
	
	public MixedLocations(Locations locations,LocationAttachments attach) {
		this.locations = locations ;
		this.attach = attach ;
	}

	public Locations getLocations() {
		return locations;
	}

	public LocationAttachments getAttach() {
		return attach;
	}

}
