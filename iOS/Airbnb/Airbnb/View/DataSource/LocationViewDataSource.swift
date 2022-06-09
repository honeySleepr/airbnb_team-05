//
//  LocationViewDataSource.swift
//  Airbnb
//
//  Created by Jason on 2022/06/10.
//

import UIKit
import MapKit

final class LocationViewDataSource: NSObject, UITableViewDataSource {
    
    private var completerResults: [MKLocalSearchCompletion]?
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return completerResults?.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LocationViewCell.identifier, for: indexPath) as? LocationViewCell else { return UITableViewCell() }
        
        if let suggestion = completerResults?[indexPath.row] {
            cell.configure(location: suggestion.title)
        }
        return cell
    }
    
}
