//
//  TumbnailCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

final class TumbnailCell: UICollectionViewCell {
    
    static let identifier = "TumbnailCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        tumbnailLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
        
    }
    
    private let tumbNailImage: UIImageView = {
        let tumbNailImage = UIImageView()
        return tumbNailImage
    }()
    
    private let superHost: UIButton = {
        let superHost = UIButton()
        superHost.setTitle("  슈퍼호스트  ", for: .normal)
        superHost.setTitleColor(UIColor.black, for: .normal)
        superHost.backgroundColor = .white
        superHost.titleLabel?.font = UIFont.systemFont(ofSize: 15, weight: .medium)
        superHost.layer.cornerRadius = 10
        return superHost
    }()
    
    private let heart: UIButton = {
        let heart = UIButton()
        heart.setImage(systemName: "heart.fill")
        heart.tintColor = .systemRed
        return heart
    }()
    
    func configure(image: UIImage?) {
        tumbNailImage.image = image
    }
    
    private func appendSubView() {
        contentView.addSubview(tumbNailImage)
        contentView.addSubview(superHost)
        contentView.addSubview(heart)
    }
    
    private func tumbnailLayout() {
        tumbNailImage.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            tumbNailImage.topAnchor.constraint(equalTo: contentView.topAnchor),
            tumbNailImage.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            tumbNailImage.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            tumbNailImage.bottomAnchor.constraint(equalTo: contentView.bottomAnchor)
        ])
        
        superHost.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            superHost.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16),
            superHost.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16)
        ])
        
        heart.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            heart.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 19),
            heart.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -17)
        ])
    }

}
