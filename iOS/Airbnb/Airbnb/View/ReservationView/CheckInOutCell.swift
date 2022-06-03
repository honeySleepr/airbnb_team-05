//
//  CheckInOutCell.swift
//  Airbnb
//
//  Created by Jason on 2022/06/03.
//

import UIKit

final class CheckInOutCell: UICollectionViewCell {
    
    static let identifier = "CheckInOutCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        checkInOutLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private let checkIn: UILabel = {
        let checkIn = UILabel()
        checkIn.numberOfLines = 0
        checkIn.textColor = .systemGray
        checkIn.font = .systemFont(ofSize: 15)
        checkIn.text = "체크인"
        return checkIn
    }()
    
    private let checkInDate: UILabel = {
        let checkInDate = UILabel()
        checkInDate.numberOfLines = 0
        let checkInDateAttributedString = NSMutableAttributedString(string: "2021년 5월 17일 오후 4:00")
        let length = checkInDateAttributedString.length
        checkInDateAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                                   .font: UIFont.systemFont(ofSize: 17)],
                                                  range: NSRange(location: 0, length: length))
        checkInDate.attributedText = checkInDateAttributedString
        return checkInDate
    }()
    
    private let checkOut: UILabel = {
        let checkOut = UILabel()
        checkOut.numberOfLines = 0
        checkOut.textColor = .systemGray
        checkOut.font = .systemFont(ofSize: 15)
        checkOut.text = "체크아웃"
        return checkOut
    }()
    
    private let checkOutDate: UILabel = {
        let checkOutDate = UILabel()
        checkOutDate.numberOfLines = 0
        let checkOutDateAttributedString = NSMutableAttributedString(string: "2021년 6월 4일 오후 12:00")
        let length = checkOutDateAttributedString.length
        checkOutDateAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                                   .font: UIFont.systemFont(ofSize: 17)],
                                                  range: NSRange(location: 0, length: length))
        checkOutDate.attributedText = checkOutDateAttributedString
        return checkOutDate
    }()
    
    func configure(InDate: String?, OutDate: String?) {
        checkInDate.text = InDate
        checkOutDate.text = OutDate
    }
    
    private func appendSubView() {
        contentView.addSubview(checkIn)
        contentView.addSubview(checkInDate)
        contentView.addSubview(checkOut)
        contentView.addSubview(checkOutDate)
    }
    
    private func checkInOutLayout() {
        checkIn.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            checkIn.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 12),
            checkIn.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            checkIn.heightAnchor.constraint(equalToConstant: 20)
        ])
        
        checkInDate.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            checkInDate.topAnchor.constraint(equalTo: checkIn.bottomAnchor, constant: 8),
            checkInDate.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            checkInDate.heightAnchor.constraint(equalToConstant: 22)
        ])
        
        checkOut.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            checkOut.topAnchor.constraint(equalTo: checkInDate.bottomAnchor, constant: 16),
            checkOut.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            checkOut.heightAnchor.constraint(equalToConstant: 20)
        ])
        
        checkOutDate.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            checkOutDate.topAnchor.constraint(equalTo: checkOut.bottomAnchor, constant: 8),
            checkOutDate.leadingAnchor.constraint(equalTo: checkIn.leadingAnchor),
            checkOutDate.heightAnchor.constraint(equalToConstant: 22),
            checkOutDate.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -12)
        ])
    }
}
