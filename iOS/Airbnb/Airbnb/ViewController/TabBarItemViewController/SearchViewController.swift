//
//  SearchViewController.swift
//  Airbnb
//
//  Created by 이건행 on 2022/05/24.
//

import UIKit

class SearchViewController: UIViewController {
    
    private var homeView = HomeView(frame: view.frame)
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        view = homeView
        homeView.setDataSource(dataSource)
        searchBarController()
        addSearchView()
    }
    
    private func searchBarController() {
        let searchController = UISearchController(searchResultsController: nil)
        searchController.searchBar.placeholder = "어디로 여행가세요?"
        searchController.hidesNavigationBarDuringPresentation = false
        searchController.automaticallyShowsCancelButton = false
        searchController.obscuresBackgroundDuringPresentation = false
        self.navigationItem.searchController = searchController
        self.navigationItem.title = "숙소 찾기"
    }
    
    private func addSearchView() {
        self.view.addSubview(homeView)
        homeView.translatesAutoresizingMaskIntoConstraints = false
        homeView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor).isActive = true
        homeView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor).isActive = true
        homeView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor).isActive = true
        homeView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor).isActive = true
    }
}
