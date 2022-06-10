//
//  SearchViewController.swift
//  Airbnb
//
//  Created by Jason on 2022/06/06.
//

import UIKit

class SearchListViewController: UIViewController, UISearchBarDelegate {
    
    private var searchView = SearchView()
    private var datasource = SearchViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationBar()
        self.view = searchView
        searchView.setupCollectionViewDelegate(datasource)
    }
    
    private func setNavigationBar() {
        let searchController = UISearchController(searchResultsController: LocationTableViewController())
        searchController.searchBar.placeholder = "어디로 여행가세요?"
        searchController.hidesNavigationBarDuringPresentation = false
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.automaticallyShowsCancelButton = false
        searchController.searchBar.delegate = self
        navigationItem.hidesSearchBarWhenScrolling = false
        navigationItem.title = "숙소 찾기"
        navigationItem.searchController = searchController
    }

}
