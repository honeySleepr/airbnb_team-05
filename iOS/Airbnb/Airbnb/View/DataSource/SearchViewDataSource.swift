//
//  SearchViewDataSource.swift
//  Airbnb
//
//  Created by 이건행 on 2022/06/07.
//

import UIKit

final class SearchViewDataSource: NSObject, UICollectionViewDataSource {
    
    var searchData: [MySearchSection] = [
        .searchListSection([MySearchSection.searchListItem].init(repeating: MySearchSection.searchListItem.init(image: UIImage(named:"seoul") ?? UIImage(), title: "서울", distance: "차로 30분 거리"), count: 10))
    ]
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return searchData.count
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch searchData[section] {
        case let .searchListSection(items):
            return items.count
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        switch searchData[indexPath.section] {
            
        case let .searchListSection(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: SearchViewCell.identifier,
                                                                for: indexPath) as? SearchViewCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(image: item.image, title: item.title, distance: item.distance)
            
            return cell
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        guard let header = collectionView.dequeueReusableSupplementaryView(ofKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: CollectionHeaderView.identifier, for: indexPath) as? CollectionHeaderView else {
            return UICollectionReusableView()
        }
        if indexPath.section == 0 {
            header.setHeaderText(text: "근처의 인기 여행지")
            header.setHeaderFontSize(size: 22)
        }
        
        return header
    }
    
    enum MySearchSection {
        case searchListSection([searchListItem])
        
        struct searchListItem {
            let image: UIImage
            let title: String
            let distance: String
        }
    }
}
