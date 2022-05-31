//
//  FavoriteSpot.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

final class FavoriteSpotView: UIView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        navigationBarLayout()

    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    let navigationBar: UINavigationBar = {
        let navigationBar = UINavigationBar()
        let navigationItem = UINavigationItem(title: "위시리스트")
        navigationBar.backgroundColor = .white
        navigationBar.setItems([navigationItem], animated: true)
        return navigationBar
    }()
    
    func navigationBarLayout() {
        let safeArea = self.safeAreaLayoutGuide
        self.addSubview(navigationBar)
        navigationBar.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            navigationBar.topAnchor.constraint(equalTo: safeArea.topAnchor),
            navigationBar.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor),
            navigationBar.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor)
        ])
        
    }
    
    
    
    
}
