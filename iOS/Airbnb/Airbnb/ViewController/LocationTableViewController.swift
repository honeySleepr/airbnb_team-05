//
//  LocationTableViewController.swift
//  Airbnb
//
//  Created by Jason on 2022/06/10.
//

import UIKit
import MapKit

final class LocationTableViewController: UIViewController {
    
    private var searchCompleter = MKLocalSearchCompleter()      // 검색을 도와주는 변수
    private var searchResults = [MKLocalSearchCompletion]()     // 검색 결과를 담는 변수
    private var searchRegion = MKCoordinateRegion(MKMapRect.world) // 검색 지역 범위를 결정하는 변수
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        setupSearchCompleter()
    }
    
    let searchBar: UISearchBar = {
        let searchBar = UISearchBar()
        searchBar.placeholder = "어디로 여행가세요?"
        return searchBar
    }()
    
    let tableView: UITableView = {
        let tableView = UITableView()
        tableView.backgroundColor = .yellow
        return tableView
    }()
    
    func setupLocationTableView() {
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(LocationViewCell.self, forCellReuseIdentifier: LocationViewCell.identifier)
        tableView.separatorStyle = .none
    }
    
}

extension LocationTableViewController: MKLocalSearchCompleterDelegate {
    
    func setupSearchCompleter() {
        searchCompleter.delegate = self
        searchCompleter.resultTypes = .address
        searchCompleter.region = searchRegion
    }
    
    func completerDidUpdateResults(_ completer: MKLocalSearchCompleter) {
        searchResults = completer.results
        // locationView.tableView.reloadData()
    }
}

extension LocationTableViewController: UISearchBarDelegate {
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        searchCompleter.queryFragment = searchText
    }
    
}
