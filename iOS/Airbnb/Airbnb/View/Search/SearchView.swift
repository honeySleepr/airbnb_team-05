//
//  SearchView.swift
//  Airbnb
//
//  Created by 이건행 on 2022/06/07.
//

import UIKit

final class SearchView: UIView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.backgroundColor = .white
        searchViewConfiguration()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private lazy var collectionView: UICollectionView = {
        
        let flowLayout = UICollectionViewFlowLayout()
        flowLayout.itemSize = CGSize(width: 350, height: 300)
        
        let collectionView = UICollectionView(frame:.zero, collectionViewLayout: self.getSearchViewLayout())
        collectionView.isScrollEnabled = true
        collectionView.showsVerticalScrollIndicator = true
        collectionView.clipsToBounds = true
        collectionView.register(CollectionHeaderView.self,
                                forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader,
                                withReuseIdentifier: CollectionHeaderView.identifier)
        collectionView.register(SearchViewCell.self, forCellWithReuseIdentifier: SearchViewCell.identifier)
        
        return collectionView
    }()
    
    func setupCollectionViewDelegate(_ dataSource: UICollectionViewDataSource) {
        collectionView.dataSource = dataSource
    }
    
    private func searchViewConfiguration() {
        addSubview(collectionView)
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            collectionView.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            collectionView.leadingAnchor.constraint(equalTo: leadingAnchor),
            collectionView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -16),
            collectionView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }
    
    func getSearchViewLayout() -> UICollectionViewCompositionalLayout {
        UICollectionViewCompositionalLayout { (sectionIndex: Int, _ ) -> NSCollectionLayoutSection? in
            return SearchViewLayout(sectionIndex: sectionIndex).create()
        }
    }
}
