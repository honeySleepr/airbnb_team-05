//
//  SearchViewLayout.swift
//  Airbnb
//
//  Created by 이건행 on 2022/06/07.
//

import UIKit

struct SearchViewLayout {
    
    private let sectionIndex: Int
    
    init(sectionIndex: Int) {
        self.sectionIndex = sectionIndex
    }
    
    func create() -> NSCollectionLayoutSection? {
        let index = searchCellList.allCases[sectionIndex]
        switch index {
        case .searchSection:
            return SearchLayoutSection().createLayout()
        }
    }
    
    struct SearchLayoutSection {
        func createLayout() -> NSCollectionLayoutSection? {
            let itemFractionalWidthFraction = 1.0
            let groupFractionalHeightFraction = 1.0 / 8.0
            let itemInset: CGFloat = 2.5
            
            let itemSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(0.5), heightDimension: .fractionalHeight(1.0))
            let item = NSCollectionLayoutItem(layoutSize: itemSize)
            item.contentInsets = NSDirectionalEdgeInsets(top: itemInset, leading: 16, bottom: 24, trailing: itemInset)
            
            let groupSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1), heightDimension: .fractionalHeight(groupFractionalHeightFraction))
            let group = NSCollectionLayoutGroup.vertical(layoutSize: groupSize, subitems: [item])
            
            let headerSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .absolute(50.0))
            let header = NSCollectionLayoutBoundarySupplementaryItem(layoutSize: headerSize, elementKind: UICollectionView.elementKindSectionHeader, alignment: .top)
            
            let section = NSCollectionLayoutSection(group: group)
            section.contentInsets = NSDirectionalEdgeInsets(top: itemInset, leading: itemInset, bottom: itemInset, trailing: itemInset)
            section.boundarySupplementaryItems = [header]
            
            return section
        }
    }
}

enum searchCellList: Int, CaseIterable {
    case searchSection = 0
    
}
