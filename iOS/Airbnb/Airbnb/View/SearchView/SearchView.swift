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
        
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private var collectionView: UICollectionView = {
        
        let flowLayout = UICollectionViewFlowLayout()
        flowLayout.sectionInset = UIEdgeInsets(top: 20.0, left: 10.0, bottom: 0.0, right: 10.0)
        flowLayout.minimumLineSpacing = 5
        flowLayout.estimatedItemSize = CGSize(width: 350, height: 240)
        
        let collectionView = UICollectionView(frame:.zero, collectionViewLayout: flowLayout)
        collectionView.isScrollEnabled = true
        collectionView.showsVerticalScrollIndicator = true
        collectionView.clipsToBounds = true
        
        return collectionView
    }()
    
}
