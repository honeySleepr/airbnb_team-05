//
//  ViewController.swift
//  Airbnb
//
//  Created by 이건행 on 2022/05/24.
//

import UIKit

class WishlistViewController: UIViewController {
    
    private var favoriteSpotView = FavoriteSpotView()
    private let datasource = FavoriteSpotViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view = favoriteSpotView
        favoriteSpotView.setDataSource(datasource)
        
    }
    
    
    
    
    
    
}

