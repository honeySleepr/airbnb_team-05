//
//  LocationTableViewController.swift
//  Airbnb
//
//  Created by Jason on 2022/06/10.
//

import UIKit
import MapKit

final class LocationTableViewController: UIViewController, UITableViewDelegate {
    
    private var searchCompleter = MKLocalSearchCompleter()      // 검색을 도와주는 변수
    private var searchResults = [MKLocalSearchCompletion]()     // 검색 결과를 담는 변수
    private var searchRegion = MKCoordinateRegion(MKMapRect.world) // 검색 지역 범위를 결정하는 변수
    
    private var locationView = LocationView()
    private var locationDatasource = LocationViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationBar()
        setupSearchCompleter()
        self.view = locationView
        locationView.delegate = self
        locationView.dataSource = locationDatasource
    }
    
    private func setNavigationBar() {
        let searchController = UISearchController(searchResultsController: nil)
        searchController.searchBar.placeholder = "어디로 여행가세요?"
        searchController.hidesNavigationBarDuringPresentation = false
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.automaticallyShowsCancelButton = false
        searchController.searchBar.delegate = self
        navigationItem.hidesSearchBarWhenScrolling = false
        navigationItem.title = "숙소 찾기"
        navigationItem.searchController = searchController
    }
    
    let searchBar: UISearchBar = {
        let searchBar = UISearchBar()
        searchBar.becomeFirstResponder()
        searchBar.keyboardAppearance = .dark
        searchBar.showsCancelButton = false
        searchBar.placeholder = "어디로 여행가세요?"
        return searchBar
    }()
    
}

extension LocationTableViewController: MKLocalSearchCompleterDelegate {
    
    func setupSearchCompleter() {
        searchCompleter.delegate = self
        searchCompleter.resultTypes = .address
        searchCompleter.region = searchRegion
    }
    
    func completerDidUpdateResults(_ completer: MKLocalSearchCompleter) {
        searchResults = completer.results
//         locationView.tableView.reloadData()
        locationView.reloadData()
    }
}

extension LocationTableViewController: UISearchBarDelegate {
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        searchCompleter.queryFragment = searchText
    }
    
}
