//
//  SearchViewController.swift
//  Airbnb
//
//  Created by Jason on 2022/06/06.
//

import UIKit

class SearchListViewController: UIViewController {
    
    private var searchView = SearchView()
    private var datasource = SearchViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationBar()
        self.view = searchView
        searchView.setupCollectionViewDelegate(datasource)
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

}

extension SearchListViewController: UISearchBarDelegate {
    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        let locationViewController = LocationTableViewController()
        navigationController?.pushViewController(locationViewController, animated: true)
        searchBar.resignFirstResponder()
    }
    
}
