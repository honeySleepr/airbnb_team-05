//
//  SearchViewDataSource.swift
//  Airbnb
//
//  Created by Jason on 2022/05/27.
//

import UIKit

final class SearchViewDataSource: NSObject, UICollectionViewDataSource {
    
    var homeData: [MyHomeSection] = [
        .first([MyHomeSection.FirstItem.init(image: UIImage(named: "heroImage") ?? UIImage())] ),
        .second([MyHomeSection.SecondItem].init(repeating: MyHomeSection.SecondItem.init(image: UIImage(named: "서울") ?? UIImage(),
                                                                                         title: "서울", distance: "차로 30분 거리"), count: 20)),
        .third([MyHomeSection.ThirdItem].init(repeating: MyHomeSection.ThirdItem.init(image: UIImage(named: "blackHouse") ?? UIImage(), title: "자연생활을 만끽할 수 있는 숙소"), count: 5))
    ]
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return homeData.count
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch homeData[section] {
        case let .first(items):
            return items.count
        case let .second(items):
            return items.count
        case let .third(items):
            return items.count
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        switch homeData[indexPath.section] {
        case let .first(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: AdvertisingCell.identifier,
                                                                for: indexPath) as? AdvertisingCell else {
                return UICollectionViewCell()
            }
            cell.configure(image: items[indexPath.item].image)
            
            return cell
        case let .second(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: NearbyDestinationCell.identifier,
                                                                for: indexPath) as? NearbyDestinationCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(image: item.image, title: item.title, distance: item.distance)
            
            return cell
        case let .third(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: LivingSpotCell.identifier,
                                                                for: indexPath) as? LivingSpotCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(image: item.image, title: item.title)
            
            return cell
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        switch kind {
        case UICollectionView.elementKindSectionHeader:
            guard let headerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: CollectionHeaderView.identifier, for: indexPath) as? CollectionHeaderView else {
                return UICollectionReusableView()
            }
            
            switch indexPath.section {
            case 1:
                headerView.setHeaderText(text: "가까운 여행지 둘러보기")
                headerView.setHeaderFontSize(size: 22)
            case 2:
                headerView.setHeaderText(text: """
                                               어디에서나,
                                               여행은 살아보는거야!
                                               """)
                headerView.setHeaderFontSize(size: 22)
            default:
                headerView.setHeaderText(text: "")
            }
            
            return headerView
        default:
            return UICollectionReusableView()
        }
    }
    
}


enum MyHomeSection {
    case first([FirstItem])
    case second([SecondItem])
    case third([ThirdItem])
    
    struct FirstItem {
        let image: UIImage
    }
    
    struct SecondItem {
        let image: UIImage
        let title: String
        let distance: String
    }
    
    struct ThirdItem {
        let image: UIImage
        let title: String
    }
}


