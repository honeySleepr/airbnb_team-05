//
//  SearchViewController.swift
//  Airbnb
//
//  Created by 이건행 on 2022/05/24.
//

import UIKit

class HomeViewController: UIViewController {
    
    private lazy var homeView = HomeView(frame: view.frame)
    private let dataSource = HomeViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        searchBarController()
        self.view = homeView
        homeView.setDataSource(dataSource)
    }
    
    private func searchBarController() {
        let searchController = UISearchController(searchResultsController: nil)
        searchController.searchBar.placeholder = "어디로 여행가세요?"
        searchController.hidesNavigationBarDuringPresentation = false
        searchController.automaticallyShowsCancelButton = false
        searchController.obscuresBackgroundDuringPresentation = false
        self.navigationItem.searchController = searchController
    }

}
