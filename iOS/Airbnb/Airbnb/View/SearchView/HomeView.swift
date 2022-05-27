//
//  SearchView.swift
//  Airbnb
//
//  Created by 이건행 on 2022/05/24.
//

import UIKit

final class HomeView: UIView {
    
    override init(frame: CGRect ){
        super.init(frame: frame)
        homeViewConfiguration()
        
        
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    private lazy var collectionView: UICollectionView = {
        let collectionView = UICollectionView(frame: .zero,
                                              collectionViewLayout: Self.getCollectionViewLayout())
        collectionView.isScrollEnabled = true
        collectionView.showsHorizontalScrollIndicator = false
        collectionView.showsVerticalScrollIndicator = false
        collectionView.clipsToBounds = true
        
        collectionView.register(AdvertisingCell.self, forCellWithReuseIdentifier: AdvertisingCell.identifier)
        collectionView.register(NearbyDestinationCell.self, forCellWithReuseIdentifier: NearbyDestinationCell.identifier)
        collectionView.register(LivingSpotCell.self, forCellWithReuseIdentifier: LivingSpotCell.identifier)
        collectionView.register(CollectionHeaderView.self,
                                        forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader,
                                        withReuseIdentifier: CollectionHeaderView.identifier)
                collectionView.translatesAutoresizingMaskIntoConstraints = false
        return collectionView
    }()
    
    func setDataSource(_ dataSource: UICollectionViewDataSource) {
        collectionView.dataSource = dataSource
    }
    
    func homeViewConfiguration() {
        addSubview(collectionView)
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            collectionView.topAnchor.constraint(equalTo: topAnchor),
            collectionView.leadingAnchor.constraint(equalTo: leadingAnchor),
            collectionView.trailingAnchor.constraint(equalTo: trailingAnchor),
            collectionView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }
    
    static func getCollectionViewLayout() -> UICollectionViewCompositionalLayout {
        UICollectionViewCompositionalLayout { (sectionIndex, env) -> NSCollectionLayoutSection? in
            switch sectionIndex {
            case 0:
                return HomeZeroLayoutSection().createLayout()
            case 1:
                return HomeFirstLayoutSection().createLayout()
            default:
                return HomeDefaultLayoutSecion().createLayout()
            }
        }
    }
    
}
