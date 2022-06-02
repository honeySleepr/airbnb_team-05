//
//  ReservationView.swift
//  Airbnb
//
//  Created by Jason on 2022/06/03.
//

import UIKit

final class ReservationView: UIView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        navigationBarLayout()
        reservationViewConfiguration()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    let navigationBar: UINavigationBar = {
        let navigationBar = UINavigationBar()
        let navigationItem = UINavigationItem(title: "예약")
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
    
    private lazy var collectionView: UICollectionView = {
        
        let flowLayout = UICollectionViewFlowLayout()
        flowLayout.sectionInset = UIEdgeInsets(top: 20.0, left: 10.0, bottom: 20.0, right: 10.0)
        flowLayout.minimumLineSpacing = 5
        flowLayout.itemSize = CGSize(width: 350, height: 300)
        
        let collectionView = UICollectionView(frame:.zero, collectionViewLayout: flowLayout)
        collectionView.isScrollEnabled = true
        collectionView.showsVerticalScrollIndicator = true
        collectionView.clipsToBounds = true
        
        collectionView.register(ReservationCell.self, forCellWithReuseIdentifier: ReservationCell.identifier)
        
        return collectionView
    }()
    
    func setDataSource(_ dataSource: UICollectionViewDataSource) {
        collectionView.dataSource = dataSource
    }
    
    func reservationViewConfiguration() {
        addSubview(collectionView)
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            collectionView.topAnchor.constraint(equalTo: navigationBar.bottomAnchor),
            collectionView.leadingAnchor.constraint(equalTo: leadingAnchor),
            collectionView.trailingAnchor.constraint(equalTo: trailingAnchor),
            collectionView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }
    
}
