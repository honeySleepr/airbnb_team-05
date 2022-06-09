//
//  SearchViewCell.swift
//  Airbnb
//
//  Created by 이건행 on 2022/06/07.
//

import UIKit

final class SearchViewCell: UICollectionViewCell {
    
    static let identifier = "SearchViewCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        searchViewLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private let searchImageView: UIImageView = {
        var imageView = UIImageView()
        return imageView
    }()
    
    private let titleLabel: UILabel = {
        let title = UILabel()
        title.textColor = .systemGray2
        title.font = .systemFont(ofSize: 17, weight: .init(rawValue: 600))
        return title
    }()
    
    private let distanceLabel: UILabel = {
        let distance = UILabel()
        distance.textColor = .systemGray3
        distance.font = .systemFont(ofSize: 17, weight: .init(rawValue: 400))
        return distance
    }()
    
    func configure(image: UIImage?, title: String?, distance: String?) {
        searchImageView.image = image
        titleLabel.text = title
        distanceLabel.text = distance
    }
    
    private func appendSubView() {
        contentView.addSubview(searchImageView)
        contentView.addSubview(titleLabel)
        contentView.addSubview(distanceLabel)
    }
    
    func searchViewLayout() {
        searchImageView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            searchImageView.topAnchor.constraint(equalTo: topAnchor),
            searchImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            searchImageView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
        
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: searchImageView.topAnchor),
            titleLabel.leadingAnchor.constraint(equalTo: searchImageView.trailingAnchor, constant: 16),
            titleLabel.heightAnchor.constraint(equalTo: searchImageView.heightAnchor, multiplier: 1/2)
        ])
        
        distanceLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            distanceLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor),
            distanceLabel.leadingAnchor.constraint(equalTo: titleLabel.leadingAnchor),
            distanceLabel.heightAnchor.constraint(equalTo: titleLabel.heightAnchor)
        ])
    }
}
